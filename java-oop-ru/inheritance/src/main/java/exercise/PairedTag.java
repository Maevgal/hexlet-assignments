package exercise;

import java.util.List;
import java.util.Map;


//Создайте класс PairedTag, который наследуется от класса Tag и описывает парный html тег. Конструктор класса принимает четыре аргумента:
//
//Имя тега в виде строки
//Атрибуты тега, которые представлены словарём Map со строковыми ключами и значениями.
//Тело тега, строка
//Список List детей. В этом задании детьми могут быть только одиночные теги.
//В классе реализуйте публичный метод toString(), который возвращает текстовое представление тега в виде строки.

// BEGIN
public final class PairedTag extends Tag {
    protected String bodyTag;
    protected List<Tag> child;

    public PairedTag(String tag, Map<String, String> attribute, String bodyTag, List<Tag> child) {
        super(tag, attribute);
        this.bodyTag = bodyTag;
        this.child = child;
    }

    //Tag div = new PairedTag(
    //    "div",
    //    Map.of("class", "y-5"),
    //    "",
    //    List.of(
    //        new SingleTag("br", Map.of("id", "s")),
    //        new SingleTag("hr", Map.of("class", "a-5"))
    //    )
    //)
    //
    //div.toString(); // "<div class="y-5"><br id="s"><hr class="a-5"></div>"
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("<");
        sb.append(this.tag);
        for (Map.Entry<String, String> atr : this.attribute.entrySet()) {
            sb.append(" ").append(atr.getKey()).append("=\"").append(atr.getValue()).append("\"");
        }
        sb.append(">");
        sb.append(bodyTag);
        for (Tag ch : child) {
            sb.append(ch);
        }
        sb.append("</").append(tag).append(">");
        return sb.toString();
    }
}
// END
