import java.util.*;
import java.io.*;

class Album implements Comparable<Album> {
    Album parent;
    String name;
    TreeSet<Album> albums;
    TreeSet<String> photos;

    Album(Album parent, String name) {
        this.parent = parent;
        this.name = name;
        this.albums = new TreeSet<>();
        this.photos = new TreeSet<>();
    }

    boolean mkalb(String albumName) {
        return albums.add(new Album(this, albumName));
    }

    int[] rmalb(Album album) {
        TreeSet<Album> albumSet = album.albums;
        TreeSet<String> photoSet = album.photos;

        int albums = albumSet.size();
        int photos = photoSet.size();

        int[] result;
        for(Album rmAlb: albumSet) {
            result = rmalb(rmAlb);
            albums += result[0];
            photos += result[1];
        }

        return new int[] {albums, photos};
    }

    boolean insertPhoto(String photo) {
        return photos.add(photo);
    }

    int deletePhoto(String photo) {
        int result = 0;

        switch(photo) {
            case "-1":
                if(photos.size() > 0) {
                    if(photos.remove(photos.first())) {
                        result++;
                    }
                }
                break;
            case "0":
                result = photos.size();
                photos.clear();
                break;
            case "1":
                if(photos.size() > 0) {
                    if(photos.remove(photos.last())) {
                        result++;
                    }
                }
                break;
            default:
                if(photos.remove(photo)) {
                    result++;
                }
                break;
        }

        return result;
    }

    @Override
    public int compareTo(Album album) {
        return this.name.compareTo(album.name);
    }
}

public class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static Album rootAlbum;
    static Album curAlbum;

    static void solution(String command, String str) {
        switch(command) {
            case "mkalb":
                if(!curAlbum.mkalb(str)) {
                    output.append("duplicated album name").append("\n");
                }
                break;
            case "rmalb":
                int[] nums = {0, 0};
                TreeSet<Album> albumSet = curAlbum.albums;
                switch (str) {
                    case "-1":
                        if (albumSet.size() > 0) {
                            Album album = albumSet.first();
                            nums = curAlbum.rmalb(album);
                            nums[0]++;
                            albumSet.remove(album);
                        }
                        break;
                    case "0":
                        for(Album album: curAlbum.albums) {
                            int[] result = curAlbum.rmalb(album);
                            nums[0] += result[0];
                            nums[1] += result[1];
                        }
                        nums[0] += curAlbum.albums.size();
                        curAlbum.albums.clear();
                        break;
                    case "1":
                        if (albumSet.size() > 0) {
                            Album album = albumSet.last();
                            nums = curAlbum.rmalb(album);
                            nums[0]++;
                            albumSet.remove(album);
                        }
                        break;
                    default:
                        Album rmAlbum = null;
                        for(Album album: albumSet) {
                            if (album.name.equals(str)) {
                                nums = curAlbum.rmalb(album);
                                nums[0]++;
                                rmAlbum = album;
                                break;
                            }
                        }
                        if(rmAlbum != null) {
                            albumSet.remove(rmAlbum);
                        }
                        break;
                }
                output.append(nums[0]).append(" ").append(nums[1]).append("\n");
                break;
            case "insert":
                if(!curAlbum.insertPhoto(str)) {
                    output.append("duplicated photo name").append("\n");
                }
                break;
            case "delete":
                output.append(curAlbum.deletePhoto(str)).append("\n");
                break;
            case "ca":
                switch(str) {
                    case "..":
                        if(curAlbum.parent != null) {
                            curAlbum = curAlbum.parent;
                        }
                        break;
                    case "/":
                        curAlbum = rootAlbum;
                        break;
                    default:
                        for(Album album: curAlbum.albums) {
                            if(album.name.equals(str)) {
                                curAlbum = album;
                            }
                        }
                        break;
                }
                output.append(curAlbum.name).append("\n");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        rootAlbum = new Album(null, "album");
        curAlbum = rootAlbum;

        int n = Integer.parseInt(input.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(input.readLine());
            String command = st.nextToken();
            String str = st.nextToken();
            solution(command, str);
        }

        System.out.println(output);
    }
}
