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
<<<<<<< HEAD
=======
  confirmMethod: Function;
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
}> = (props) => {
  const value = props.name2;
  let flag: boolean = true;
  if (value === null) {
    flag = false;
  }

  const handleClose = () => {
    props.setStatus(false);
<<<<<<< HEAD
  }

  const handleConfirm = () => { // 모달에서 yes를 눌렀을때의 동작
    //props.doConfirm 정도로?
  }
=======
  };

  const handleConfirm = () => {
    props.confirmMethod();
  };
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

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

<<<<<<< HEAD
            <style.ModalButton onClick={handleConfirm} style={{ marginRight: "10px" }}>
              yes
            </style.ModalButton>
            <style.ModalButton onClick={handleClose} style={{ marginLeft: "10px" }}>
=======
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
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
              no
            </style.ModalButton>
          </style.ModalInfo>
        </style.Modal>
      </style.Contatiner>
    </Fragment>
  );
};

export default Modal;
