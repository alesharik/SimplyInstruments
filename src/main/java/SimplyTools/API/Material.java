package SimplyTools.API;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Material {
	public String name;
	public int quality;
	public float speed;
	public ArrayList<String> oreDict;

	public Material(String name, int quality, float speed, ArrayList<String> oreDict) {
		this.name = name;
		this.quality = quality;
		this.speed = speed;
		this.oreDict = oreDict;
	}

	public Material(String name, int quality, float speed, String[] oreDict) {
		this.name = name;
		this.quality = quality;
		this.speed = speed;
		this.oreDict = new ArrayList<String>();
		for(String oreDictName : oreDict) {
			this.oreDict.add(oreDictName);
		}
	}

	public Material() {
	}

	public JSONObject getDescription() {
		JSONObject ret = new JSONObject();
		ret.put("name", this.name);
		ret.put("quality", this.quality);
		ret.put("speed", this.speed);
		ret.put("oreDict", this.oreDict);
		return ret;
	}
}
