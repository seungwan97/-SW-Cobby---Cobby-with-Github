import { Fragment } from "react";
import Image from "next/image";
import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";

const QuestItem = (props: any) => {
  return (
    <style.QuestItemWrapper>
      <TextBox size={20} content={"QuestItem입니다."} />
      <TextBox
        size={20}
        content={`[${props.item.category}] ${props.item.title} ${props.item.goal}`}
      />
      <TextBox size={20} content={`${props.item.progress}`} />
      <Image src={props.item.award} alt="아이템" width={80} height={65} />
    </style.QuestItemWrapper>
  );
};

export default QuestItem;
