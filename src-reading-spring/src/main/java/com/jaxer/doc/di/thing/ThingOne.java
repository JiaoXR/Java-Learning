package com.jaxer.doc.di.thing;

/**
 * Created by jaxer on 2018/12/2
 */
public class ThingOne {
	private ThingTwo thingTwo;

	public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {

	}

	public void setThingTwo(ThingTwo thingTwo) {
		this.thingTwo = thingTwo;
	}
}
