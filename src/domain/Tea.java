package domain;

/**
 * Чай.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class Tea {
    private long id;
    private String name;
    private TeaType teaType;

    public Tea(long id, String name, TeaType teaType) {
        this.id = id;
        this.name = name;
        this.teaType = teaType;
    }

    public long getId() {
        return id;
    }

    public TeaType getTeaType() {
        return teaType;
    }
}
