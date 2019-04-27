package edu.uark.uarkregisterapp.models.api.interfaces;

import org.json.JSONObject;

import edu.uark.uarkregisterapp.models.api.Item;
import edu.uark.uarkregisterapp.models.api.Product;

public interface LoadFromJsonInterface<T> {
	T loadFromJson(JSONObject rawJsonObject);
	//Item loadFromJson(JSONObject rawJsonObject);
}
