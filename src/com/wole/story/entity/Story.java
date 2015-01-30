package com.wole.story.entity;

import java.io.Serializable;

public class Story
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String Author;
  private String content;
  private String date;
  private String title;
  private String url;
  private int viewCount;
 

  public String getAuthor()
  {
    return this.Author;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getDate()
  {
    return this.date;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getUrl()
  {
    return this.url;
  }

  public int getViewCount()
  {
    return this.viewCount;
  }

  public void setAuthor(String paramString)
  {
    this.Author = paramString;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public void setViewCount(int paramInt)
  {
    this.viewCount = paramInt;
  }

  public String toString()
  {
    return "Story [title=" + this.title + ", Author=" + this.Author + ", date=" + this.date + ", url=" + this.url + "]";
  }
}