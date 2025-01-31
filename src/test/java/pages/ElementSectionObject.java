package pages;
import commonUtils.BasePage;
import commonUtils.WebElementWrapper.E2EElement;
import commonUtils.WebElementWrapper.E2EElementList;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElementSectionObject extends BasePage {

    E2EElement fullNameTextField = new E2EElement(By.id("userName"));
    E2EElement fullEmailTextField = new E2EElement(By.id("userEmail"));
    E2EElement currentAddressTextField = new E2EElement(By.id("currentAddress"));
    E2EElement permanentAddressTextField = new E2EElement(By.id("permanentAddress"));
    E2EElement submitButton = new E2EElement(By.id("submit"));
    E2EElement submittedNameText = new E2EElement(By.id("name"));
    E2EElement submittedEmailText = new E2EElement(By.id("email"));
    E2EElement submittedCurrentAddressText = new E2EElement(By.id("currentAddress"));
    E2EElement submittedPermanentAddressText = new E2EElement(By.id("permanentAddress"));
    E2EElement noCheckboxSelected = new E2EElement(By.xpath("//div[contains(@class, 'display-result mt-4')]"));
    E2EElement yesRadioButton = new E2EElement(By.id("yesRadio"));
    E2EElement noRadioButton = new E2EElement(By.id("noRadio"));
    E2EElement impressiveRadioButton = new E2EElement(By.id("impressiveRadio"));
    E2EElement searchInTableTextField = new E2EElement(By.id("searchBox"));


    // Constructor
    public ElementSectionObject(WebDriver driver) {
        super(driver);
    }

    // Locators for navigating side menu
    public E2EElement getSideMenuOptionLocator(String textOption) {
        String xpath = ("//span[contains(@class, 'text') and text()='"+ textOption +"']");
        return new E2EElement(By.xpath(xpath));
    }

    // Get checkbox by label next to it
    public E2EElement getCheckboxByLabel(String labelText) {
        String xpath = "//span[@class='rct-title' and text()='" + labelText + "']/preceding-sibling::span[@class='rct-checkbox']";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getExpandArrowByLabel(String labelText) {
        String xpath = "//label[@for='tree-node-" + labelText + "']/preceding-sibling::button[contains(@class, 'rct-collapse rct-collapse-btn')]";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getRecordRow(String firstName, String lastName) {
        String xpath = "//table//tr[td[text()='" + firstName + "'] and td[text()='" + lastName + "']]";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getDeletePersonButton(String name) {
        String xpath = "//div[@class='rt-tr-group'][contains(., '" + name + "')]//span[starts-with(@id, 'delete-record')]";
        return new E2EElement(By.xpath(xpath));
    }

    public String getInfoFromPersonInTable(String name, String fieldName) {
        String xpath = "";

        // Construindo o XPath com base no nome do campo desejado
        switch (fieldName.toLowerCase()) {
            case "name":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][1]"; // Nome
                break;
            case "lastname":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][2]"; // Sobrenome
                break;
            case "age":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][3]"; // Idade
                break;
            case "email":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][4]"; // E-mail
                break;
            case "salary":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][5]"; // Salário
                break;
            case "department":
                xpath = "//div[@class='rt-tr-group'][contains(., '"+ name +"')]//div[@class='rt-td'][6]"; // Departamento
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + fieldName);
        }

        E2EElement fieldElement = new E2EElement(By.xpath(xpath));

        return fieldElement.text();
    }

    public boolean isRecordDeleted(String name) {
        String xpath = "//td[text()='" + name + "']";
        try {
            E2EElement recordName = new E2EElement(By.xpath(xpath));
            return !recordName.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public E2EElement getRecordsInTable(String name) {
        String xpath = "//div[@class='rt-td'][contains(.,'" + name + "')]";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getButtonMessageAfterClick(String message){
        String xpath = "//p[contains(.,'"+ message +"')]";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getInputFieldById(String fieldId) {
        return new E2EElement(By.id(fieldId));
    }

    public E2EElement getEditPersonButton(String name) {
        String xpath = "//div[@class='rt-tr-group'][contains(., '" + name + "')]//span[starts-with(@id, 'edit-record')]";
        return new E2EElement(By.xpath(xpath));
    }

    public E2EElement getClickButtonsByType(String clickType) {
        String xpath = "//button[starts-with(text(), '" + clickType + "')]";
        return new E2EElement(By.xpath(xpath));
    }
    public E2EElement demoQASections(String cardName){
        String xpath = ("//h5[text()='" + cardName + "']");
        return new E2EElement(By.xpath(xpath));
    }

    // URL verification
    public void verifySectionByUrl(String expectedSection) {
        String actualUrl = driver.getCurrentUrl();

        switch (expectedSection.toLowerCase()) {
            case "elements":
                Assert.assertTrue("URL does not contain 'elements'", actualUrl.contains("elements"));
                break;

            case "forms":
                Assert.assertTrue("URL does not contain 'forms'", actualUrl.contains("forms"));
                break;

            case "alerts, frame & windows":
                Assert.assertTrue("URL does not contain 'alertsWindows'", actualUrl.contains("alertsWindows"));
                break;

            case "widgets":
                Assert.assertTrue("URL does not contain 'widgets'", actualUrl.contains("widgets"));
                break;

            case "interactions":
                Assert.assertTrue("URL does not contain 'interaction'", actualUrl.contains("interaction"));
                break;

            case "book store application":
                Assert.assertTrue("URL does not contain 'books'", actualUrl.contains("books"));
                break;

            default:
                throw new IllegalArgumentException("Section '" + expectedSection + "' is not recognized");
        }
    }

    // Actions for form filling
    public void fillFullName(String name) {
        fullNameTextField.sendKeys(name);
    }

    public void fillFullEmail(String email) {
        fullEmailTextField.sendKeys(email);
    }

    public void fillCurrentAddress(String address) {
        currentAddressTextField.sendKeys(address);
    }

    public void fillPermanentAddress(String address) {
        permanentAddressTextField.sendKeys(address);
    }

    public void submitSurvey() {
        submitButton.scrollIntoView();
        submitButton.click();
    }

    // Survey elements check
    public boolean surveyElementsPresent() {
        try {
            return  submittedNameText.isDisplayed() &&
                    submittedEmailText.isDisplayed() &&
                    submittedCurrentAddressText.isDisplayed() &&
                    submittedPermanentAddressText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean surveySubmittedNameComparison(String name) {
        try {
            String submittedNameTxt = submittedNameText.text().trim();
            return submittedNameTxt.contains(name); // Checks if the text contains the expected name
        } catch (Exception e) {
            return false;
        }
    }

    public boolean surveySubmittedEmailComparison(String email) {
        try {
            String submittedEmailTxt = submittedEmailText.text().trim();
            return submittedEmailTxt.contains(email); // Checks if the text contains the expected email
        } catch (Exception e) {
            return false;
        }
    }

    public E2EElement expandCheckboxArrowByLabel(String labelText) {
        return getExpandArrowByLabel(labelText);
    }
    public E2EElement desiredCheckBox(String checkboxText) {
        return getCheckboxByLabel(checkboxText);
    }

    public boolean isElementSelected(String labelText) {
        try {
            // Locate the element by XPath
            String xpath = "//div[contains(@class, 'display-result mt-4')]//span[contains(@class, 'text-success') and text()='" + labelText + "']";
            E2EElement element = new E2EElement(By.xpath(xpath));

            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areElementsSelected(String... labelTexts) {
        for (String labelText : labelTexts) {
            if (!isElementSelected(labelText)) {
                return false;
            }
        }
        return true;
    }

    public boolean noItemsSelected() {
        try {
            return !noCheckboxSelected.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRadioButton(String radioButtonId) {
        String xpath = "";

        switch (radioButtonId.toLowerCase()) {
            case "yes":
                xpath = "//label[@for='yesRadio']";
                break;

            case "no":
                xpath = "//label[@for='noRadio']";
                break;

            case "impressive":
                xpath = "//label[@for='impressiveRadio']";
                break;

            default:
                throw new IllegalArgumentException("Unknown radio button: " + radioButtonId);
        }

        E2EElement radioButtonLabel = new E2EElement(By.xpath(xpath));
        radioButtonLabel.click();
    }


    public E2EElement getRadioButtonSelector(String radioButton) {
        switch (radioButton.toLowerCase()) {
            case "yes":
                return yesRadioButton;

            case "no":
                return noRadioButton;

            case "impressive":
                return impressiveRadioButton;

            default:
                throw new IllegalArgumentException("Unknown radio button: " + radioButton);
        }
    }

    public E2EElement getAddNewTableRecord() {
        String id = "addNewRecordButton";
        return new E2EElement(By.id(id));
    }

    public boolean verifyRecordInTable(String firstName, String lastName, String age, String email, String salary, String department) {
        // Mapeando os valores esperados
        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("firstName", firstName);
        expectedValues.put("lastName", lastName);
        expectedValues.put("age", age);
        expectedValues.put("email", email);
        expectedValues.put("salary", salary);
        expectedValues.put("department", department);

        // Percorrendo as chaves do map (cada campo da tabela)
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String column = entry.getKey();
            String expectedValue = entry.getValue();

            // Selecionando todas as células que contêm o texto correspondente
            String xpath = "//div[@class='rt-td'][contains(.,'" + expectedValue + "')]";
            E2EElementList cells = new E2EElementList(By.xpath(xpath));
            ArrayList<E2EElement> cellList = cells.getList();

            // Se não encontrar nenhuma célula que contenha o texto esperado, retorna false
            if (cellList.isEmpty()) {
                return false;
            }

            // Caso contrário, verifica se o valor da célula corresponde ao valor esperado
            boolean matchFound = false;
            for (E2EElement cell : cellList) {
                if (cell.text().contains(expectedValue)) {
                    matchFound = true;
                    break;
                }
            }

            // Se o valor não for encontrado na célula, retorna false
            if (!matchFound) {
                return false;
            }
        }

        // Se todos os valores foram encontrados, retorna true
        return true;
    }

    public boolean searchPersonInTable(String name){
        searchInTableTextField.sendKeys(name);
    return getRecordsInTable(name).isDisplayed();
    }
}
