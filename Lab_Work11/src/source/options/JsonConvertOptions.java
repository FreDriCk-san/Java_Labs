package source.options;

public class JsonConvertOptions {

    public Formatting jsonStyle;
    public LineBreaks lineBreaks;

    public JsonConvertOptions() {

        this.jsonStyle = Formatting.Minify;
        this.lineBreaks = LineBreaks.Disable;
    }
}