package org.cjohnson.slimedetector.world;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Objects;

public class SlimeRadio {
    private final int rangeChunks;
    private final boolean[][] slimeMatrix;
    private final ArrayList<Chunk> slimeChunks;

    public SlimeRadio(int rangeChunks) {
        this.rangeChunks = rangeChunks;
        this.slimeMatrix = new boolean[(2 * rangeChunks) + 1][(2 * rangeChunks) + 1];
        slimeChunks = new ArrayList<>();
    }

    public void sample(Location location) {
        if(location == null) throw new IllegalArgumentException("Provided location was null.");
        slimeChunks.clear();

        int chunkX = location.getBlockX() >> 4;
        int chunkZ = location.getBlockZ() >> 4;

        int pointerI, pointerJ, pointerX, pointerZ;
        Chunk pointerChunk;
        for(int i = 0; i < slimeMatrix.length; i++) {
            for(int j = 0; j < slimeMatrix[i].length; j++) {
                pointerI = i - rangeChunks;
                pointerJ = j - rangeChunks;

                pointerX = chunkX + pointerI;
                pointerZ = chunkZ + pointerJ;

                pointerChunk = Objects.requireNonNull(location.getWorld()).getChunkAt(pointerX, pointerZ);
                slimeMatrix[i][j] = pointerChunk.isSlimeChunk();
                if(pointerChunk.isSlimeChunk()) slimeChunks.add(pointerChunk);
            }
        }
    }

    public boolean[][] getSlimeMatrix() {
        return slimeMatrix;
    }

    public ArrayList<Chunk> getSlimeChunks() {
        return slimeChunks;
    }
}
