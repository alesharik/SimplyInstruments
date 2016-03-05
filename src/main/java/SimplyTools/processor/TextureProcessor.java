package SimplyTools.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import SimplyTools.ConfigHandler;
import SimplyTools.Helpers;
import SimplyTools.SimplyTools;
import SimplyTools.API.Material;
import SimplyTools.API.MaterialRegister;

public class TextureProcessor extends Thread {

	public static File textureConfig = new File(ConfigHandler.baseDirName + "/TextureConfig.json");
	public static File customTextureLocation = new File(ConfigHandler.baseDirName + "/customTextures");
	private static ArrayList<File> configConveyor = new ArrayList<File>();
	public static HashMap<String, HashMap<String, File>> textures = new HashMap<String, HashMap<String, File>>();

	@Override
	public void start() {
		if(!textureConfig.exists()) {
			createTextureConfig();
		}
		if(!customTextureLocation.exists()) {
			customTextureLocation.mkdir();
		}
		this.setName("TextureProcessorThread");
		run();
	}

	public void createTextureConfig() {
		JSONArray allOfThisStuff = new JSONArray();
		JSONObject partTextures = new JSONObject();
		for(int i = 0; i < SimplyTools.parts.size(); i++) {
			JSONObject partTexture = new JSONObject();
			partTexture.put("name", SimplyTools.parts.get(i).getUnlocalizedName());
			for(Material currentMaterial : MaterialRegister.materials) {
				partTexture.put(currentMaterial.name, "default");
			}
			partTextures.putAll(partTexture);
		}
		allOfThisStuff.add(partTextures);
		Helpers.createJSON(textureConfig, allOfThisStuff);
	}

	public void run() {
		System.out.println("Thread is alive!");
		addTextureConfig(textureConfig);
		readTextures();
		this.interrupt();
	}

	public void addTextureConfig(File texConf) {
		configConveyor.add(texConf);
	}

	public void readTextures() {
		configConveyor = configConveyor.stream().filter((f) -> f.exists()).collect(Collectors.toCollection(ArrayList::new));
		for(File config : configConveyor) {
			try {
				JSONArray conf = (JSONArray) Helpers.parseJSONFile(config);
				conf.forEach((confPart) -> {
					JSONObject part = (JSONObject) confPart;
					String name = (String) part.get("name");
					HashMap<String, File> partTextures = new HashMap<String, File>();
					for(Material currentMaterial : MaterialRegister.materials) {
						partTextures.put(currentMaterial.name, stringToFile((String) part.get(currentMaterial.name)));
					}
					textures.put(name, partTextures);
				});
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private File stringToFile(String s) {
		if(s.equals("default") || !(new File(customTextureLocation + s).exists())) {
			return null;
		}
		return new File(customTextureLocation + s);
	}
}
