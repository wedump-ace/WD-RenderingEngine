package com.badlogic.androidgames.framework.gl;

import com.badlogic.androidgames.framework.impl.GLGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ObjLoader {
    public static Vertices3 load(GLGame game, String file) {
        InputStream in = null;
        try {
            in = game.getFileIO().readAsset(file);
            List<String> lines = readLines(in);

            float[] vertices = new float[lines.size() * 3];
            float[] normals = new float[lines.size() * 3];
            float[] uv = new float[lines.size() * 2];

            int numVertices = 0;
            int numNormals = 0;
            int numUV = 0;
            int numFaces = 0;

            int[] facesVerts = new int[lines.size() * 3];
            int[] facesNormals = new int[lines.size() * 3];
            int[] facesUV = new int[lines.size() * 3];
            int vertexIndex = 0;
            int normalIndex = 0;
            int uvIndex = 0;
            int faceIndex = 0;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.startsWith("v ")) {
                    String[] tokens = line.split("[ ]+");
                    vertices[vertexIndex++] = Float.parseFloat(tokens[1]);
                    vertices[vertexIndex++] = Float.parseFloat(tokens[2]);
                    vertices[vertexIndex++] = Float.parseFloat(tokens[3]);
                    numVertices++;
                    continue;
                }
                if (line.startsWith("vn ")) {
                    String[] tokens = line.split("[ ]+");
                    normals[normalIndex++] = Float.parseFloat(tokens[1]);
                    normals[normalIndex++] = Float.parseFloat(tokens[2]);
                    normals[normalIndex++] = Float.parseFloat(tokens[3]);
                    numNormals++;
                    continue;
                }
                if (line.startsWith("vt ")) {
                    String[] tokens = line.split("[ ]+");
                    uv[uvIndex++] = Float.parseFloat(tokens[1]);
                    uv[uvIndex++] = Float.parseFloat(tokens[2]);
                    numUV++;
                    continue;
                }
                if (line.startsWith("f ")) {
                    String[] tokens = line.split("[ ]+");

                    for (int j = 1; j <= 3; j++) {
                        String[] parts = tokens[j].split("/");

                        facesVerts[faceIndex] = getIndex(parts[0], numVertices);
                        if (parts.length > 1)
                            facesUV[faceIndex] = getIndex(parts[1], numUV);
                        if (parts.length > 2)
                            facesNormals[faceIndex] = getIndex(parts[2], numNormals);
                        faceIndex++;
                    }

                    numFaces++;
                    continue;
                }
            }

            float[] verts = new float[(numFaces * 3) * (3 + (numNormals > 0 ? 3 : 0) + (numUV > 0 ? 2 : 0))];

            for (int i = 0, vi = 0; i < numFaces * 3; i++) {
                int vertexIdx = facesVerts[i] * 3;
                verts[vi++] = vertices[vertexIdx];
                verts[vi++] = vertices[vertexIdx + 1];
                verts[vi++] = vertices[vertexIdx + 2];

                if (numUV > 0) {
                    int uvIdx = facesUV[i] * 2;
                    verts[vi++] = uv[uvIdx];
                    verts[vi++] = 1 - uv[uvIdx + 1];
                }
                if (numNormals > 0) {
                    int normalIdx = facesNormals[i] * 2;
                    verts[vi++] = normals[normalIdx];
                    verts[vi++] = normals[normalIdx + 1];
                    verts[vi++] = normals[normalIdx + 2];
                }
            }

            Vertices3 model = new Vertices3(game.getGLGraphics(), numFaces * 3, 0, false, numUV > 0, numNormals > 0);
            model.setVertices(verts, 0, verts.length);
            return model;
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load '" + file + "'", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
    }

    static List<String> readLines(InputStream in) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    static int getIndex(String index, int size) {
        int idx = Integer.parseInt(index);
        if (idx < 0) {
            return size + idx;
        } else {
            return idx - 1;
        }
    }
}
