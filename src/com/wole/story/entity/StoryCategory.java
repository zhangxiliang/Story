package com.wole.story.entity;

import java.io.Serializable;

public class StoryCategory implements Serializable
{
  private String type;
  private String url;

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