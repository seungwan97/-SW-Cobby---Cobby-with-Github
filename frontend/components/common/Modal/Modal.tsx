import React, { Fragment } from "react";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import * as style from "./style/Modal";

const Modal: React.FC<{
  name: string;
  name2: string;
  yes: boolean;
  no: boolean;
  setStatus: Function;
  confirmMethod: Function;
}> = (props) => {
  const value = props.name2;
  let flag: boolean = true;
  if (value === null) {
    flag = false;
  }

  const handleClose = () => {
    props.setStatus(false);
  };

  const handleConfirm = () => {
    props.confirmMethod();
  };

  return (
    <Fragment>
      <style.Contatiner>
        <style.Modal>
          <style.CloseButton onClick={handleClose}>
            <FontAwesomeIcon icon={faXmark} />
          </style.CloseButton>
          <style.ModalInfo>
            <span>{props.name}</span>
            <br />
            {flag && <span>{value}</span>}
            {flag && <br />}

            <style.ModalButton
              onClick={handleConfirm}
              style={{ marginRight: "10px" }}
            >
              yes
            </style.ModalButton>
            <style.ModalButton
              onClick={handleClose}
              style={{ marginLeft: "10px" }}
            >
              no
            </style.ModalButton>
          </style.ModalInfo>
        </style.Modal>
      </style.Contatiner>
    </Fragment>
  );
};

export default Modal;
