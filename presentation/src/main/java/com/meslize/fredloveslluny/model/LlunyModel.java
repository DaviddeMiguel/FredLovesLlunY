package com.meslize.fredloveslluny.model;

public class LlunyModel {
  private String id;
  private String name;
  private double distance;

  public LlunyModel(Builder builder) {
    id = builder.id;
    name = builder.name;
    distance = builder.distance;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getDistance() {
    return distance;
  }

  public static final class Builder {
    private String id;
    private String name;
    private double distance;

    public Builder() {
    }

    public Builder setId(String val) {
      id = val;
      return this;
    }

    public Builder setName(String val) {
      name = val;
      return this;
    }

    public Builder setDistance(double val) {
      distance = val;
      return this;
    }

    public LlunyModel build() {
      return new LlunyModel(this);
    }
  }
}
