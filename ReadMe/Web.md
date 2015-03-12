# Web

##### 1-19:learn about webView:
+ `<WebView></WebView>`
+ `(WebView)findViewById()`
+ `webView.getSettings().setJavaScriptEnabled(true)`	
+ `webView.setWebViewClient(new WebViewClient(){})`
+ `shouldOverrideUrlLoading()` 
+ `webView.loadUrl(url)`

##### 2-2 -2-3 http,xml,json
+ `http`
 - `HttpURLConnection`
 
			URL url = new URL(“http://www.baidu.com”)
      	 	HttpURLConnection connection = (HttpURLConnection)utl.
      	 	openConnection();       	      connection.setRequestMethod(“GET”);
        	connection.setConnectTimeout(8000)
        	connection.setReadTimeout(8000)
        	InputStream in = connection.getInputStream();
        	connection.disconnect();
 - `HttpClient`
    `get`方式发送http请求
		
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(“http://www.baidu.com”);
       		HttpClient.execute(httpGet);

	`post`方式发送http请求
		
		HttpPost httpPost = new HttpPost(“http://www.baidu.com”);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
      	params.add(new BasicNameValuePair(“username”,”flyer”);
		params.add(new BasicNameValuePair(“password”,”123456”);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,”utf-8”);
        httpPost.setEntity(params);
        httpPost.execute(httpPost)
        
     获取`Entity`
        
        if(httpResponse.getStatusLine().getStatusCode() ==200){
        	HttpEntity entity = httpResponse.getEntity();
            String response = EntityUtils.toString(entity);
        }
		
+ `xml`
 - Pull解析xml
 		//获取实例
		
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
       	 	xmlPullParser.setInput(new StringReader(response));
       	 	int eventType = xmlPullParser.getEventType();
            App app = new App();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //解析某个节点
                    case XmlPullParser.START_TAG: {
                        if (“id”.equals(nodeName)) {
                            app.setId(xmlPullParser.nextText());
                        } else if (“name”.equals(nodeName)) {
                            app.setName(xmlPullParser.nextText());
                        } else if (“version”.equals(nodeName)) {
                            app.setVersion(xmlPullParser.nextText());
                        }
                        break;
                    }
                    //完成某个节点解析
                    case XmlPullParser.END_TAG: {
                        if (“app”.equals(nodeName)) {
                            Log.e(“id: “,app.id+””);
                            Log.e(“name: “,app.name+””);
                            Log.e(“id: “,app.version+””);
                            Message message = new Message();
                            message.what = SHOW_RESPONSE;
                            message.obj = app;
                            handler.sendMessage(message);
                        }
                        break;
                    }
                    default:
                        break;
                }
                //调到下一个节点
                eventType = xmlPullParser.next();
            }
 - SAX解析xml
 	`MainActivity`
 	    
 	    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        	XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
      		MyHandler handler = new MyHandler();
     		xmlReader.setContentHandler(handler);
        	xmlReader.parse(new InputSource(new StringReader(xmlData)));
    `MyHandler`继承`DefaultHandler`
    	
    		public void startDocument() throws SAXException {
        	id = new StringBuilder();
        	name = new StringBuilder();
        	version = new StringBuilder();
    		}
    		public void startElement(String uri, 
    		String localName, String qName, Attributes attributes) throws SAXException {
        	nodeName = localName;
    		}
    		public void characters(char[] ch, int start, int length) throws SAXException {
        	//根据节点名字将内容添加到对应的StringBuilder中
        	switch (nodeName){
           	 case “id”:
           	     id.append(ch,start,length);
           	     break;
           	 case “name”:
            	    name.append(ch,start,length);
           	     break;
                case “version”:
                	version.append(ch,start,length);
      	  	}
   	 		}
   	 	
    		public void endElement(String uri, String localName, String qName) throws SAXException {
        	if (“app”.equals(localName)){
            	Log.e(“ContentHandler”,”id is “+id);
            	Log.e(“ContentHandler”,”name is “+name);
	            Log.e(“ContentHandler”,”version is “+version);
    	        //清空StringBuilder
        	    id.setLength(0);
            	name.setLength(0);
	            version.setLength(0);
    	    }
    		}
	    	public void endDocument() throws SAXException {
    		}
+ `json`
 - `JSONObject`
 	    
 	    	JSONArray jsonArray = new JSONArray(response);
        	for (int i = 0;i<jsonArray.length();i++){
        	JSONObject jsonObject = jsonArray.getJSONObject(i);
            Game game = new Game();
            game.id = jsonObject.getString(“id”);
            game.name = jsonObject.getString(“name”);
            game.version = jsonObject.getString(“version”);
            Message message = new Message();
            message.obj = game;
            handler.sendMessage(message);
        	}
 - `GSON`
 	   
 	   		Gson gson = new Gson();
       		 List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        	for (App app : appList){
            Log.e(“parseJSONWithGSON”,”id is “+app.id);
            Message message = new Message();
            message.obj = app;
            handler.sendMessage(message);
        	}

