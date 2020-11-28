package net.sinlo.vchat.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ParameterServletRequestWrapper  extends HttpServletRequestWrapper{
    private Map<String , String[]> params = new HashMap<String, String[]>();

    public ParameterServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 重载一个构造方法
     * @param request
     * @param extendParams
     */
    public ParameterServletRequestWrapper(HttpServletRequest request , Map<String , String[]> extendParams) throws IOException {
        this(request);
        addAllParameters(extendParams);
    }

    @Override
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addAllParameters(Map<String , String[]> otherParams) {
        for(Map.Entry<String , String[]>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }


    public void addParameter(String name , Object value) {
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }

}
