package com.wole.story.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StoryCategory implements Serializable
{
  private String type;
  private String url;
  private HashMap paramMap;
  private int total;



public HashMap getParamMap() {
	return paramMap;
}

public void setParamMap(HashMap paramMap) {
	this.paramMap = paramMap;
}

public int getTotal() {
	return total;
}

public void setTotal(int total) {
	this.total = total;
}

public String getType()
  {
    return this.type;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}