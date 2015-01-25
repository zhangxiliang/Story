package com.wole.story.entity;

public class Author
{
  private String href;
  private String index;
  private String name;

  public String getHref()
  {
    return this.href;
  }

  public String getIndex()
  {
    return this.index;
  }

  public String getName()
  {
    return this.name;
  }

  public void setHref(String paramString)
  {
    this.href = paramString;
  }

  public void setIndex(String paramString)
  {
    this.index = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String toString()
  {
    return "Author [name=" + this.name + ", href=" + this.href + "]";
  }
}