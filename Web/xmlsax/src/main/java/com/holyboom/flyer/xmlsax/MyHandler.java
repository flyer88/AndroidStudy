package com.holyboom.flyer.xmlsax;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by flyer on 15/2/2.
 */
public class MyHandler extends DefaultHandler{

    String nodeName;
    StringBuilder id;
    StringBuilder name;
    StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据节点名字将内容添加到对应的StringBuilder中
        switch (nodeName){
            case "id":
                id.append(ch,start,length);
                break;
            case "name":
                name.append(ch,start,length);
                break;
            case "version":
                version.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)){
            Log.e("ContentHandler","id is "+id);
            Log.e("ContentHandler","name is "+name);
            Log.e("ContentHandler","version is "+version);
            //清空StringBuilder
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {

    }
}
