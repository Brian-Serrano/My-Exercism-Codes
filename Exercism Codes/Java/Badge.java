class Badge {
    public String print(Integer id, String name, String department) {
        return (id == null ? "" : "[" + id + "] - ") + name + " - " + (department == null ? "OWNER" : department.toUpperCase());
    }
}
