import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Registration functionality', () => {
    beforeEach(() => {
        browser.get('/login');
    });

    

    it('should fail to register for empty credentials', () => {

        it('should log in for valid credentials', () => {
            element(by.css('input[type=""]')).sendKeys('test1');
            element(by.css('input[type="password"]')).sendKeys('test1');
            element(by.buttonText('Bejelentkezés')).click();
           //logged in
           element(by.buttonText('Új taszk')).click();
           element(by.css('input[type="text"]')).sendKeys("e2e taszk1");
           element(by.css('input[type="number"]')).sendKeys(1);
           element(by.css('input[type=""]')).sendKeys("asd");
                      
           expect(by.css('style[color="#f44336"]')).toEqual("Tölts ki minden mezőt!");
        });
    });


    it('should fail to register for empty credentials', () => {
        
        it('should log in for valid credentials', () => {
            element(by.css('input[type=""]')).sendKeys('test1');
            element(by.css('input[type="password"]')).sendKeys('test1');
            element(by.buttonText('Bejelentkezés')).click();
            //logged in
            element(by.buttonText('Új taszk')).click();
            element(by.css('input[type="text"]')).sendKeys("e2e taszk1");
            element(by.css('input[type="number"]')).sendKeys(1);
            element(by.css('input[type=""]')).sendKeys("2017-08-01 11:20");
                        
            expect(by.css('style[color="#f44336"]')).toEqual("");
        });
    });

    

    
});