public class Duke {
    public static void greet(String string){
        System.out.println("____________________________________________________________");
        System.out.println(string);
        System.out.println("Hello! I'm your chatbot - Huei.");
        System.out.println("What can I do for you? Type 'bye' to exit.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = "  _   _    _   _ U _____ u             \n"
                    + " |'| |'|U |\"|u| |\\| ___\"|/    ___      \n"
                    + "/| |_| |\\\\| |\\| | |  _|\"     |_\"_|     \n"
                    + "U|  _  |u | |_| | | |___      | |      \n"
                    + " |_| |_| <<\\___/  |_____|   U/| |\\u    \n"
                    + " //   \\\\(__) )(   <<   >>.-,_|___|_,-. \n"
                    + "(_\") (\"_)   (__) (__) (__)\\_)-' '-(_/  \n";
        greet(logo);

    }
}
