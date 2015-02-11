package org.jemz.jf.json.internal.objects;

import org.jemz.jf.json.internal.util.IJFConstants;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jmartinez on 2/2/15.
 */
public class JFParamArray implements IJFConstants {




    private String cl;

    private List<JFParam> paramArray = new ArrayList<JFParam>();

    private int dim = 0;

    private static Map<String, Class<?>> arrayType = new HashMap<String, Class<?>>();

    static {
        arrayType.put(JAVA_TYPE_BOOLEANARRAY, boolean.class);
        arrayType.put(JAVA_TYPE_CHARACTERARRAY, char.class);
        arrayType.put(JAVA_TYPE_INTEGERARRAY, int.class);
        arrayType.put(JAVA_TYPE_FLOATARRAY, float.class);
        arrayType.put(JAVA_TYPE_DOUBLEARRAY, double.class);
        arrayType.put(JAVA_TYPE_LONGARRAY, long.class);

    }

    public JFParamArray() {

    }

    public JFParamArray( Object o) {
        if(o != null) {
            this.cl = o.getClass().getName();
            this.dim = countArrayDimension();

            for (int i = 0; i < Array.getLength(o); i++) {
                try {

                    paramArray.add( new JFParam("e" + i, Array.get(o, i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            paramArray = null;
        }

    }


    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public List<JFParam> getParamArray() {
        return paramArray;
    }

    public void setParamArray(List<JFParam> paramArray) {
        this.paramArray = paramArray;
    }

    public Object getArray() throws Exception {
        Class clazz;
        dim = countArrayDimension();

        if(dim == 1) {
            clazz = arrayType.get(cl);
            if(clazz == null) {
                clazz = Class.forName(cl.substring(2, cl.length()-1));
            }
        } else {
            clazz = Class.forName(cl.substring(dim-1));
        }

        int len = 0;
        if(paramArray != null) {
            len = paramArray.size();
        }
        Object o = Array.newInstance(clazz, len);
        for(int i=0;i<len;i++) {
            Array.set(o, i, paramArray.get(i).toObject());
        }
        return o;
    }

    public int countArrayDimension()
    {
        int i;
        for (i=0; i < cl.length(); i++)
        {
            if (cl.charAt(i) != '[')
            {
                break;
            }
        }
        return i;
    }

}


