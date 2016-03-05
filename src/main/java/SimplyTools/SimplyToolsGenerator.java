package SimplyTools;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class SimplyToolsGenerator extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		BiomeGenBase b = w.getBiomeGenForCoords(x, z);
		if(b.biomeName.equals("Plains") && r.nextInt() <= 10){
			SimplyToolsUndergrondHouse.generate(w, x, 100, z);
		}
		return true;
	}
	public static class SimplyInstrumentsSGenerator implements IWorldGenerator {
		@Override
		public void generate(Random r, int x, int z, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
			SimplyToolsGenerator g = new SimplyToolsGenerator();
			g.generate(w, r, x, r.nextInt(64), z);
		}	
	}

}

