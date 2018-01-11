import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Registration functionality', () => {
    beforeEach(() => {
        browser.get('/login');
    });

    

    it('should fail to register for empty credentials', () => {
        element(by.buttonText('Regisztráció')).click();
        element(by.id('namefield')).sendKeys('E2E teszt1');
        element(by.css('input[type="password"]')).sendKeys('E2E pass1');
        element(by.css('input[type="email"]')).sendKeys('');
        element(by.buttonText('Regisztráció2')).click();
        expect(getPath()).toEqual('/login');
    });

    it('should fail to register for empty credentials', () => {
        element(by.buttonText('Regisztráció')).click();
        element(by.id('namefield')).sendKeys('E2E teszt1');
        element(by.css('input[type="password"]')).sendKeys('');
        element(by.css('input[type="email"]')).sendKeys('e2e email');
        element(by.buttonText('Regisztráció2')).click();
        expect(getPath()).toEqual('/login');
    });

    it('should register for valid credentials', () => {
        element(by.buttonText('Regisztráció')).click();
        element(by.id('namefield')).sendKeys('E2E teszt1');
        element(by.css('input[type="password"]')).sendKeys('E2E pass1');
        element(by.css('input[type="email"]')).sendKeys('e2e email');
        element(by.buttonText('Regisztráció2')).click();
        element(by.buttonText('Vissza')).click();
        element(by.buttonText('Bejelentkezés')).click();
        expect(getPath()).toEqual('/folder');
    });


    it('should fail for duplicate register', () => {
        element(by.buttonText('Regisztráció')).click();
        element(by.id('namefield')).sendKeys('E2E teszt1');
        element(by.css('input[type="password"]')).sendKeys('E2E pass1');
        element(by.css('input[type="email"]')).sendKeys('e2e email');
        element(by.buttonText('Regisztráció2')).click();
        element(by.buttonText('Vissza')).click();
        element(by.buttonText('Bejelentkezés')).click();
        expect(getPath()).toEqual('/login');
    });

    

    
});