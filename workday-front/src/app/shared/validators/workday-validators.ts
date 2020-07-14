import {Injectable} from "@angular/core";
import {AbstractControl, ValidationErrors} from "@angular/forms";
import {DateEnum} from "../enums/date-enum";
import {EmailEnum} from "../enums/email-enum";
import {PersonalIdentifierEnum} from "../enums/personal-identifier-enum";
import {PhoneNumberEnum} from "../enums/phone-number-enum";
import {NumbersEnum} from "../enums/numbers-enum";


@Injectable({
  providedIn: 'root'
})
export class WorkdayValidators {

  static validDate(date: AbstractControl): ValidationErrors {
    if (date.pristine) {
      return null;
    }
    if (date.value && !date.value.match(DateEnum.WORKDAY_DEFAULT_DATE_FORMAT_REGEX.toString())) {
      return {'invalidDate': true}
    }
    return null;
  }

  static validEmail(email: AbstractControl): ValidationErrors {
    if (email.pristine) {
      return null;
    }
    if (email.value && !email.value.match(EmailEnum.WORKDAY_DEFAULT_EMAIL_FORMAT_REGEX.toString())) {
      return {'invalidEmail': true}
    }
    return null;
  }

  static validPesonalIdentifier(personalIdentifier: AbstractControl): ValidationErrors {
    if (personalIdentifier.pristine) {
      return null;
    }
    if (personalIdentifier.value && !personalIdentifier.value.match(PersonalIdentifierEnum.WORKDAY_DEFAULT_PERSONAL_IDENTIFIER_FORMAT_REGEX.toString())) {
      console.log("da");
      return {'invalidPersonalIdentifier': true}
    }
    return null;
  }

  static validPhoneNumber(phoneNumber: AbstractControl): ValidationErrors {
    if (phoneNumber.pristine) {
      return null;
    }
    if (phoneNumber.value && !phoneNumber.value.match(PhoneNumberEnum.WORKDAY_DEFAULT_PHONE_NUMBER_FORMAT_REGEX.toString())) {
      return {'invalidPhoneNumber': true}
    }
    return null;
  }

  static validNumber(streetNumber: AbstractControl): ValidationErrors {
    if (streetNumber.pristine) {
      return null;
    }
    if (streetNumber.value && !streetNumber.value.match(NumbersEnum.WORKDAY_DEFAULT_STREET_NUMBER_FORMAT_REGEX.toString())) {
      return {'invalidStreetNumber': true}
    }
    return null;
  }

  static validPostalCode(postalCode: AbstractControl): ValidationErrors {
    if (postalCode.pristine) {
      return null;
    }
    if (postalCode.value && !postalCode.value.match(NumbersEnum.WORKDAY_DEFAULT_POSTAL_CODE_FORMAT_REGEX.toString())) {
      return {'invalidPostalCode': true}
    }
    return null;
  }
}
