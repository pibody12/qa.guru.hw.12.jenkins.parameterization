package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement monthSelect = $(".react-datepicker__month-select");
    private SelenideElement yearSelect = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year) {
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        String formateDay = String.format("%02d", Integer.parseInt(day));
        $(".react-datepicker__day--0" + formateDay +
                ":not(.react-datepicker__day--outside-month)").click();
    }
}
