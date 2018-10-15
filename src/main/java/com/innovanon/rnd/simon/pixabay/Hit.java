/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author gouldbergstein
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name="hits")
public class Hit {
	@XmlElement(name="largeImageURL")
	//URL largeImageURL;
	String largeImageURL;
	@XmlElement(name="webformatHeight")
	int webformatHeight;
	@XmlElement(name="webformatWidth")
	int webformatWidth;
	@XmlElement(name="likes")
	int likes ;
	@XmlElement(name="imageWidth")
	int imageWidth ;
	@XmlElement(name="id")
	int id ;
	@XmlElement(name="user_id")
	int user_id ;
	@XmlElement(name="views")
	int views ;
	@XmlElement(name="comments")
	int comments ;
	@XmlElement(name="pageURL")
	//URL pageURL ;
	String pageURL;
	@XmlElement(name="imageHeight")
	int imageHeight ;
	@XmlElement(name="webformatURL")
	//URL webformatURL ;
	String webformatURL;
	@XmlElement(name="type")
	String type ;
	@XmlElement(name="previewHeight")
	int previewHeight ;
	//@XmlElementWrapper(name="tags")
	//@XmlJavaTypeAdapter(CommaSeparatedListAdapter.class)
	//List<String> tags;
	@XmlElement(name="tags")
	String tags;
	@XmlElement(name="downloads")
	int downloads ;
	@XmlElement(name="user")
	String user ;
	@XmlElement(name="favorites")
	int favorites ;
	@XmlElement(name="imageSize")
	int imageSize ;
	@XmlElement(name="previewWidth")
	int previewWidth ;
	@XmlElement(name="userImageURL")
	//URL userImageURL ;
	String userImageURL ;
	@XmlElement(name="previewURL")
	//URL previewURL ;
	String previewURL;
/*
 *   "largeImageURL": "https://pixabay.com/get/ef32b40a2fe90021d85a5854e74c4491e272e3d104b014429df8c77da4e5b2_1280.jpg",
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
 */
}
