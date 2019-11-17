/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xestionfranquicia;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author erifc
 */
public class TitleXML extends DefaultHandler{
    
    private ArrayList<Title> titles;
    
    private Title tempTitle;
    
    private String txt;
    
    public TitleXML(){
        super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(localName.equalsIgnoreCase("channel")){
            this.titles = new ArrayList<>();
        }else if(localName.equalsIgnoreCase("title")){
            this.tempTitle = new Title();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if(localName.equalsIgnoreCase("title")){
            this.tempTitle.setTitle(txt);
            this.titles.add(this.tempTitle);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
            this.txt = new String(ch,start,length);
    }
    
    public ArrayList<Title> getTitles(){
        return titles;
    }
    
    
    
    
    
    
}
