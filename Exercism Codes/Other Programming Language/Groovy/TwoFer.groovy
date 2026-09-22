class TwoFer {

    static String twoFer(String name = "you") {
        name = name.empty ? "you" : name
        "One for $name, one for me."
    }
}
