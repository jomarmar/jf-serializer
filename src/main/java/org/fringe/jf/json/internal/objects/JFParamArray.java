package org.fringe.jf.json.internal.objects;

import org.fringe.jf.json.internal.util.JFSonUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Array;

/**
 * Created by jmartinez on 2/2/15.
 */
public class JFParamArray {


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
            Class clazz = null;
            dim = countArrayDimension();

            if(cl.endsWith("[C")) {
                if(dim == 1) {
                    clazz = char.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith("[I")) {
                if(dim == 1) {
                    clazz = int.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith("[J")) {
                if(dim == 1) {
                    clazz = long.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith("[F")) {
                if(dim == 1) {
                    clazz = float.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith("[D")) {
                if(dim == 1) {
                    clazz = double.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }
            } else if (cl.endsWith("[B")) {
                if(dim == 1) {
                    clazz = byte.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }

            } else if (cl.endsWith("[Z")) {
                if(dim == 1) {
                    clazz = boolean.class;
                } else {
                    clazz = Class.forName(cl.substring(dim -1));
                }

            }
//        else if (cl.equals("[Ljava.lang.String;")) {
//            clazz = String.class;
//            return paramArray;
//        }
            else if (cl.equals("[Lcom.dtg.puma.common.objects.JFParam;")) {
                if(paramArray == null) {
                    paramArray = new JFParam[0];
                }
                return paramArray;
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
                Array.set(o, i, JFSonUtil.toObject(paramArray[i]));
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


