package org.jemz.jf.json.internal.objects;

import org.jemz.jf.json.internal.util.IJFConstants;


import java.lang.reflect.Array;

/**
 * Created by jmartinez on 2/2/15.
 */
public class JFParamArray implements IJFConstants {


        private String cl;

        private JFParam[] paramArray;

        private int dim = 0;

        public JFParamArray() {

        }

        public JFParamArray( Object o) {
            if(o != null) {
                this.cl = o.getClass().getName();
                this.dim = countArrayDimension();
                if (o instanceof JFParam[]) {
                    paramArray = (JFParam[]) o;
                } else {
                    paramArray = new JFParam[Array.getLength(o)];
                    for (int i = 0; i < Array.getLength(o); i++) {
                        try {

                            paramArray[i] = new JFParam("e" + i, Array.get(o, i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

        public JFParam[] getParamArray() {
            return paramArray;
        }

        public void setParamArray(JFParam[] paramArray) {
            this.paramArray = paramArray;
        }

        public Object getArray() throws Exception {
            Class clazz;
            dim = countArrayDimension();

            if(cl.endsWith(JAVA_TYPE_CHARACTERARRAY)) {
                if(dim == 1) {
                    clazz = char.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith(JAVA_TYPE_INTEGERARRAY)) {
                if(dim == 1) {
                    clazz = int.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith(JAVA_TYPE_LONGARRAY)) {
                if(dim == 1) {
                    clazz = long.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith(JAVA_TYPE_FLOATARRAY)) {
                if(dim == 1) {
                    clazz = float.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith(JAVA_TYPE_DOUBLEARRAY)) {
                if(dim == 1) {
                    clazz = double.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith(JAVA_TYPE_BOOLEANARRAY)) {
                if(dim == 1) {
                    clazz = boolean.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }

            } else {
                if(dim == 1) {
                    clazz = Class.forName(cl.substring(2, cl.length()-1));
                } else {
                    clazz = Class.forName(cl.substring(dim-1));
                }
            }
            int len = 0;
            if(paramArray != null) {
                len = paramArray.length;
            }
            Object o = Array.newInstance(clazz, len);
            for(int i=0;i<len;i++) {
                Array.set(o, i, paramArray[i].toObject());
            }
            return o;
        }

        public int countArrayDimension()
        {
            int count = 0;
            for (int i=0; i < cl.length(); i++)
            {
                if (cl.charAt(i) == '[')
                {
                    count++;
                }
            }
            return count;
        }

    }


