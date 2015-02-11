package org.jemz.jf.json.internal.serializer;

import com.google.gson.stream.JsonWriter;
import org.jemz.jf.json.internal.objects.JFObject;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.objects.JFParamArray;
import org.jemz.jf.json.internal.util.IJFConstants;
import org.jemz.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jmartinez on 2/10/15.
 */
public class JFXmlSerializer implements IJFConstants {

    private static final Logger logger = LoggerFactory.getLogger(JFXmlSerializer.class);
    private static XMLOutputFactory xmlof = XMLOutputFactory.newInstance();

    private XMLStreamWriter jswriter = null;

    public JFXmlSerializer() {

    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    public String writeJFParam(JFParam param) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        writeJFParam(param, bos);
        jswriter.close();
        return bos.toString();
    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeJFParam(JFParam param, String file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        writeJFParam(param, fos);
    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @param ostream the ostream
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeJFParam(JFParam param, OutputStream ostream) throws Exception {
        jswriter = xmlof.createXMLStreamWriter(new OutputStreamWriter(ostream, DEFAULT_ENCODING));
        internalWriteJFParam(param);
    }






    @SuppressWarnings("unchecked")
    private void internalWriteJFParam(JFParam obj) throws IOException {
        switch(type) {
            case JFDataTypes.TYPE_BASE64:
                jswriter.name(FIELD_VALUE).value((String)obj);
                break;
            case JFDataTypes.TYPE_OBJECT:

                try {
                    writeJFObject((JFObject)obj);
                } catch (Exception e) {
                    logger.error("TYPE_OBJECT: " + e.toString(), e);
                }
                break;
            case JFDataTypes.TYPE_OBJECTARRAY:
                try {
                    writeJFObjectArray((JFParamArray)obj);
                } catch (Exception e1) {
                    logger.error("TYPE_OBJECTARRAY: " + e1.toString(), e1);
                }
                break;
            case JFDataTypes.TYPE_LIST:
            case JFDataTypes.TYPE_VECTOR:
                List<JFParam> orig = (List<JFParam>)obj;
                Iterator<JFParam> iter = orig.iterator();
                int i = 0;
                jswriter.name(FIELD_VALUE);
                jswriter.beginArray();
                while(iter.hasNext()) {
                    try {
                        //JFParam p = new JFParam("elem" + (i++), iter.next());
                        internalWriteJFParam(iter.next());
                    } catch (Exception e) {
                        logger.error("TYPE_VECTOR: " + e.toString(), e);
                    }

                }

                jswriter.endArray();
                break;
            case JFDataTypes.TYPE_TABLE:
            case JFDataTypes.TYPE_MAP:
                jswriter.name(FIELD_VALUE);
                jswriter.beginArray();
                Map<JFParam, JFParam> origMap = (Map<JFParam, JFParam>) obj;
                for (Map.Entry<JFParam, JFParam> entry : origMap.entrySet()) {
                    try {
                        jswriter.beginObject();
                        //Object key = entry.next();
                        JFParam keyParam = entry.getKey();
                        JFParam valParam = entry.getValue();
                        jswriter.name(FIELD_MAP_KEY);
                        internalWriteJFParam(keyParam);
                        jswriter.name(FIELD_MAP_VALUE);
                        internalWriteJFParam(valParam);
                        jswriter.endObject();
                    } catch (Exception e) {
                        logger.error("TYPE_MAP: " + e.toString(), e);
                    }
                }
                jswriter.endArray();
                break;
            default:

                jswriter.name(FIELD_VALUE);
                gson.toJson(obj, Object.class, jswriter);


        }


    }

    private void writeJFObject(JFObject jfobj) throws Exception {

        jswriter.name(FIELD_VALUE);
        jswriter.beginObject();
        jswriter.name(FIELD_CLASS).value(jfobj.getClazz());
        jswriter.name(FIELD_ATTRIBUTE);
        jswriter.beginArray();

        Iterator<JFParam> param = jfobj.getAttr().iterator();
        while(param.hasNext()) {
            JFParam jfp = param.next();
            internalWriteJFParam(jfp);

        }
        jswriter.endArray();


        jswriter.endObject();
    }

    private void writeJFObjectArray(JFParamArray obj) throws Exception {
        //String arrayType = obj.getClass().getName();
        jswriter.name(FIELD_CLASS).value(obj.getCl());
        jswriter.name(FIELD_VALUE);


        JFParam[] objA = obj.getParamArray();
        jswriter.beginArray();
        for(int i = 0; i < objA.length; i++) {
            internalWriteJFParam(objA[i]);
        }
        jswriter.endArray();


    }

}
