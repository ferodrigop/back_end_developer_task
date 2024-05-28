package com.rpindv.backend_task.entities;

public enum WhiteListFileTypes {
    // Common
    PDF("pdf"),
    DOCX("docx"),
    GIF("gif"),
    TXT("txt"),

    // Graphics and Textures
    JPEG("jpeg", "jpg"),
    PNG("png"),
    TIFF("tif", "tiff"),
    DDS("dds"),
    TGA("tga"),
    BMP("bmp"),

    // 3D Models
    FBX("fbx"),
    OBJ("obj"),
    DAE("dae"),
    GLTF("gltf", "glb"),
    STL("stl"),

    // Audio Files
    WAV("wav"),
    MP3("mp3"),
    OGG("ogg"),
    FLAC("flac"),

    // Video Files
    MP4("mp4"),
    AVI("avi"),
    MOV("mov"),

    // Scripts and Source Code
    CPP("cpp", "h"),
    CS("cs"),
    PY("py"),
    LUA("lua"),

    // Shaders
    GLSL("glsl", "vert", "frag"),
    HLSL("hlsl"),
    CG("cg"),

    // Configuration and Data Files
    XML("xml"),
    JSON("json"),
    YAML("yaml"),
    INI("ini"),

    // Game Engine Specific Files
    UNITY("unity", "prefab", "asset"),
    UNREAL("uasset", "umap"),
    GODOT("tscn", "tres"),

    // Archives and Compressed Files
    ZIP("zip"),
    RAR("rar"),
    SEVEN_ZIP("7z"),

    // Executable Files
    EXE("exe"),
    APP("app"),
    APK("apk"),
    IPA("ipa");

    private final String[] extensions;

    WhiteListFileTypes(String... extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public static boolean isAllowed(String extension) {
        for (WhiteListFileTypes fileType : WhiteListFileTypes.values()) {
            for (String ext : fileType.getExtensions()) {
                if (ext.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }
}
