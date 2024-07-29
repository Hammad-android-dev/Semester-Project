package com.example.ecommereceapp.domain;

import java.io.Serializable;

public class PopularDomain implements Serializable
{
    private String title;
    private String picurl;
    private int review;
    private double score;
    private int numberInCart;
    private double price;
    private String description;

    public PopularDomain(String title, String picurl, int review, double score, double price, String description)
    {
        this.title = title;
        this.picurl = picurl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description=description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPicurl()
    {
        return picurl;
    }

    public void setPicurl(String picurl)
    {
        this.picurl = picurl;
    }

    public int getReview()
    {
        return review;
    }

    public void setReview(int review)
    {
        this.review = review;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public int getNumberInCart()
    {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart)
    {
        this.numberInCart = numberInCart;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getPicUrl()
    {
        return picurl;
    }
}
