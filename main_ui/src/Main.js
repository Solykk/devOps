import './Admin.css';
import {useTranslation} from "react-i18next";
import "./i18n";
import {BrowserRouter, Route, Routes} from "react-router-dom"
import {useEffect, useState} from "react";
import api from "./api";

function Main() {

    const { t } = useTranslation();

    const [alertText, setAlertText] = useState(undefined);

    const [page, setPage] = useState(undefined);
    const [message, setMessage] = useState('');

    const [updatePage, setUpdatePage] = useState(false);
    useEffect(() => {
        setAlertText(undefined);
        api.find(0)
            .then((response) => {
                setPage(response.data);
                setMessage('');
            })
            .catch((error) => setAlertText(error.message));
    }, [updatePage])

    function sendMessage() {
        api.create(message)
            .then(() => setUpdatePage(!updatePage))
            .catch((error) => setAlertText(error.message));
    }

  const main = (
    <div className="Main">
      <header className="Main-header">
          {
              alertText &&
              <p style={{color:"red"}}>
                  {alertText}
              </p>
          }
          <p>
              {t("welcome")}
          </p>
          <div>
              <input placeholder={'Enter message'}
                     value={message}
                     onChange={(event) => setMessage(event.currentTarget.value)}/>
              <button onClick={() => sendMessage()}>Send</button>
          </div>
          <div>
              <p>Last Messages:</p>
              {page &&
                  page.content.map((value, index) => (
                      <div key={index}>
                          <p>{value.id + ' ' + value.message + ' ' + value.address + ' '
                              + value.created.toString().substring(0, 10) + '-' + value.created.toString().substring(11, 19)}</p>
                      </div>
                  ))
              }
          </div>
      </header>
    </div>
  );

  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={main} />
        </Routes>
      </BrowserRouter>
  )
}

export default Main;
