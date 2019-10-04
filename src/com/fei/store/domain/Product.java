package com.fei.store.domain;

public class Product implements Comparable<Product> {

    private String pid;
    private String pname;
    private double price;
    private String pimage;
    private String cid;
    private Description pdesc;


    @Override
    public int compareTo(Product o) {
        int i = (int) (this.getPrice() - o.getPrice());//先按照年龄排序
        return i;
    }

    public Product() {

    }

    public Product(String pname, double price, String cid, Description pdesc) {
		super();
		this.pname = pname;
		this.price = price;
		this.cid = cid;
		this.pdesc = pdesc;
	}

	public Product(String pid, String pname, double price, String pimage, String cid) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.pimage = pimage;
        this.cid = cid;
    }

    public Product(String pname, double price, String pimage, String cid, Description pdesc) {
		super();
		this.pname = pname;
		this.price = price;
		this.pimage = pimage;
		this.cid = cid;
		this.pdesc = pdesc;
	}
    
    public Product(String pid, String pname, double price, String pimage, String cid, Description pdesc) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.pimage = pimage;
		this.cid = cid;
		this.pdesc = pdesc;
	}

	@Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pimage='" + pimage + '\'' +
                ", cid='" + cid + '\'' +
                ", pdesc='" + pdesc + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

	public Description getPdesc() {
		return pdesc;
	}

	public void setPdesc(Description pdesc) {
		this.pdesc = pdesc;
	}


}
