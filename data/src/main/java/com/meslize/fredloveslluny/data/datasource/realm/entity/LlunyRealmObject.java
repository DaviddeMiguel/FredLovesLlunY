package com.meslize.fredloveslluny.data.datasource.realm.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LlunyRealmObject extends RealmObject {
  @PrimaryKey private String id;
  private String name;
  private double distance;

  public LlunyRealmObject() {
  }

  public LlunyRealmObject(String id, String name, double distance) {
    this.id = id;
    this.name = name;
    this.distance = distance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }
}
