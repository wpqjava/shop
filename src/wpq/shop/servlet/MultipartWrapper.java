package wpq.shop.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import wpq.shop.util.RequestUtil;

public class MultipartWrapper extends HttpServletRequestWrapper {
	Map<String,String[]> params = null;

	public MultipartWrapper(HttpServletRequest request) {
		super(request);
		setParams(request);
	}

	private void setParams(HttpServletRequest req) {
		params = new HashMap<String,String[]>();
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator item = upload.getItemIterator(req);
			while(item.hasNext()){
				FileItemStream fis = item.next();
				is = fis.openStream();
				if(fis.isFormField()){
					setFormParams(fis,is);
				}else {
					setMulParams(fis,is,req,baos);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null)is.close();
				if(baos!=null)baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setMulParams(FileItemStream fis, InputStream is, HttpServletRequest req,ByteArrayOutputStream baos) throws IOException {
		if(is.available()>0){
			String fileName = FilenameUtils.getName(fis.getName());
			String extName = FilenameUtils.getExtension(fileName);
			if(checkExt(extName)){
				params.put("fileName", new String[]{fileName});
				params.put(fis.getFieldName(), new String[]{fileName});
				baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len = 0;
				while((len=is.read(buf))>0){
					baos.write(buf,0,len);
				}
				byte[] fs = baos.toByteArray();
				req.setAttribute("fs", fs);
			}else{
				Map<String,String> errors = (Map<String, String>) req.getAttribute("errors");
				errors.put(fis.getFieldName(), "图片文件格式不正确");
			}
		}
	}
	
	private boolean checkExt(String extName) {
		for(String str:RequestUtil.ALLOWEDExt){
			if(str.equalsIgnoreCase(extName))return true;
		}
		return false;
	}

	private void setFormParams(FileItemStream fis, InputStream is) throws IOException {
		String key = fis.getFieldName();
		if(params.containsKey(key)){
			String[] values = params.get(key);
			values = Arrays.copyOf(values, values.length+1);
			values[values.length-1] = Streams.asString(is);
		}else{
			params.put(key, new String[]{Streams.asString(is)});
		}
	}

	@Override
	public String getParameter(String name) {
		String[] value = params.get(name);
		if(value!=null)return value[0];
		return null;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] value = params.get(name);
		if(value!=null)return value;
		return null;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

}
