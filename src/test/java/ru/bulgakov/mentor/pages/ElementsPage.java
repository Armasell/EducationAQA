package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class ElementsPage {

    private final SelenideElement textBox = $$(".menu-list li").get(0);
    private final SelenideElement fullNameInput = $("[placeholder='Full Name']");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement nameResultField = $("#name");

    private final SelenideElement checkBox = $$(".menu-list li").get(1);
    private final SelenideElement homeTreeSwitcher = $(".rc-tree-switcher");
    private final SelenideElement desktopTreeSwitcher = $x("/html/body/div/div/div/div/div[2]/div[1]/div/div[3]/div/div/div/div[2]/span[2]");
    private final SelenideElement notesCheckBox = $("[aria-label='Select Notes']");
    private final SelenideElement checkBoxResultField = $("#result");

    public ElementsPage clickTextBox() {
        textBox.click();
        return this;
    }

    public ElementsPage setName(String name) {
        fullNameInput.setValue(name);
        return this;
    }

    public ElementsPage submit() {
        submitButton.click();
        return this;
    }

    public ElementsPage verifyName(String nameResult) {
        nameResultField.shouldHave(text(nameResult));
        return this;
    }

    public ElementsPage clickCheckBox() {
        checkBox.click();
        return this;
    }

    public ElementsPage openHomeTree() {
        homeTreeSwitcher.click();
        return this;
    }

    public ElementsPage openDesktopTree() {
        desktopTreeSwitcher.click();
        return this;
    }

    public ElementsPage chooseNotes() {
        notesCheckBox.click();
        return this;
    }

    public ElementsPage verifyCheckBoxResult(String checkBoxResult) {
        checkBoxResultField.shouldHave(text(checkBoxResult));
        return this;
    }
}
