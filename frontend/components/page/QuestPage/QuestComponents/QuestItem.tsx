import Image from "next/image";
import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";
import QuestConfirmButton from "./QuestConfirmButton";
import ProgressBar from "@ramonak/react-progress-bar";

const QuestItem = (props: any) => {
  return (
    <style.QuestItemWrapper>
      <TextBox
        size={25}
        content={`[${props.item.category}] ${props.item.title} ${props.item.goal}`}
      />
      <style.QuestInfoWrapper>
        <style.ColumContentWrapper>
          <style.ProgressWrapper>
            <style.CustomProgressBar
              bgColor={"#333333"}
              borderRadius={"0px"}
              completed={`${props.item.progress}`}
            />
            <TextBox size={20} content={`${props.item.progress}%`} />
          </style.ProgressWrapper>
          <QuestConfirmButton />
        </style.ColumContentWrapper>
        <style.ImageWrapper>
          <style.CustomImage
            src={props.item.award}
            alt="아이템"
            width={80}
            height={65}
          />
        </style.ImageWrapper>
      </style.QuestInfoWrapper>
    </style.QuestItemWrapper>
  );
};

export default QuestItem;
