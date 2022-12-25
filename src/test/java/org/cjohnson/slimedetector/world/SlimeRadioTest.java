package org.cjohnson.slimedetector.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlimeRadioTest {
    private World mockWorld;
    private Chunk[][] mockChunks;
    private final boolean[][] chunkMatrix = {{true,  false, false},
                                             {false, true,  false},
                                             {false, false, true }};
    private Location mockAnchor;

    private final SlimeRadio slimeRadio = new SlimeRadio(1);

    @BeforeEach
    void eachSetup() {
        mockWorld = mock(World.class);

        mockAnchor = mock(Location.class);

        when(mockAnchor.getBlockX()).thenReturn(0);
        when(mockAnchor.getBlockZ()).thenReturn(0);
        when(mockAnchor.getWorld()).thenReturn(mockWorld);

        mockChunks = new Chunk[3][3];
        for(int i = 0; i < mockChunks.length; i++) {
            for(int j = 0; j < mockChunks[i].length; j++) {
                mockChunks[i][j] = mock(Chunk.class);
                when(mockChunks[i][j].isSlimeChunk()).thenReturn(chunkMatrix[i][j]);
            }
        }

        for(int i = 0; i < mockChunks.length; i++) {
            for(int j = 0; j < mockChunks[i].length; j++) {
                when(mockWorld.getChunkAt(i-1, j-1)).thenReturn(mockChunks[i][j]);
            }
        }
    }

    @Test
    void mockAnchorTest() {
        assertEquals(0, mockAnchor.getBlockX());
        assertEquals(0, mockAnchor.getBlockZ());
        assertEquals(mockWorld, mockAnchor.getWorld());
    }

    @Test
    void mockChunksTest() {
        for(int i = 0; i < mockChunks.length; i++) {
            for(int j = 0; j < mockChunks[i].length; j++) {
                assertEquals(mockChunks[i][j], mockWorld.getChunkAt(i-1, j-1));
            }
        }
    }

    @Test
    void sampleTest() {
        slimeRadio.sample(mockAnchor);
        assertArrayEquals(chunkMatrix, slimeRadio.getSlimeMatrix());
    }
}
