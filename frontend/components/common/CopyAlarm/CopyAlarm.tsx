import { useState, useEffect } from "react";
import * as style from "./style/CopyAlarm";
import TextBox from "../TextBox/TextBox";

const CopyAlarm = (props: any) => {
    const [showMessage, setShowMessage] = useState(false);

    useEffect(() => {
        if (props.isVisible) {
            setShowMessage(true);
            const timer = setTimeout(() => {
                setShowMessage(false);
                props.setIsVisible(false);
            }, 2000);
            return () => clearTimeout(timer);
        }
    }, [props.isVisible]);

    return (
        <style.CopyMsgWrapper style={{ display: showMessage ? "flex" : "none" }}>
            <style.IsCopiedMsg>
                <TextBox size={25} content="복사되었습니다." color="white" />
            </style.IsCopiedMsg>
        </style.CopyMsgWrapper>
    );
};

export default CopyAlarm;