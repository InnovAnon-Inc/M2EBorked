/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gouldbergstein
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="")
public class PixabayResult {
	@XmlElement(name="totalHits")
	int totalHits;
	@XmlElement(name="hits", type=Hit.class)
	List<Hit> hits;
	@XmlElement(name="total")
	int total;
	
/*
 * {
  "totalHits": 500,
  "hits": [
    {
      "largeImageURL": "https://pixabay.com/get/ef32b40a2fe90021d85a5854e74c4491e272e3d104b014429df8c77da4e5b2_1280.jpg",
      "webformatHeight": 335,
      "webformatWidth": 640,
      "likes": 162,
      "imageWidth": 5137,
      "id": 67557,
      "user_id": 1897,
      "views": 36085,
      "comments": 21,
      "pageURL": "https://pixabay.com/en/nuclear-weapons-test-nuclear-weapon-67557/",
      "imageHeight": 2696,
      "webformatURL": "https://pixabay.com/get/ef32b40a2fe90021d85a5854e74c4491e272e3d104b014429df8c77da4e5b2_640.jpg",
      "type": "photo",
      "previewHeight": 78,
      "tags": "nuclear weapons test, nuclear weapon, weapons test",
      "downloads": 13518,
      "user": "WikiImages",
      "favorites": 149,
      "imageSize": 3023786,
      "previewWidth": 150,
      "userImageURL": "https://cdn.pixabay.com/user/2012/06/03/15-50-21-3_250x250.jpg",
      "previewURL": "https://cdn.pixabay.com/photo/2012/11/28/09/32/nuclear-weapons-test-67557_150.jpg"
    },
    
    ...
    
     ],
  "total": 684
 */
}
