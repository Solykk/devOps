import i18n from "i18next";
import {initReactI18next} from "react-i18next";
import LanguageDetector from "i18next-browser-languagedetector";

import UA from "./translations/ua.json";
import EN from "./translations/en.json";
import moment from "moment";

i18n
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        fallbackLng: 'ua',
        keySeparator: false, // we do not use keys in form messages.welcome
        resources: {
            en: {
                translation: EN
            },
            ua: {
                translation: UA
            }
        },
        interpolation: {
            escapeValue: false, // react already safes from xss
            format: function (value, format, lng) {
                if (value instanceof Date) return moment(value).format(format);
                if (typeof value === "number") return new Intl.NumberFormat().format(value);
                return value;
            }
        }
    }).then(() => console.log("i18n init complete"));
