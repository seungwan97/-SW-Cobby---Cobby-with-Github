import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";
import QuestConfirmButton from "./QuestConfirmButton";

interface Props {
  questData: {
    questId: number;
    questName: string;
    questType: string;
    questGoal: number;
    progress: number;
    award: {
      costumeId: number;
      name: string;
      category: string;
      questId: number;
      imgUrl: string;
      gifUrl: string;
    };
  };
}
const QuestItem = (props: Props) => {
  const { questData } = props;
  return (
    <style.QuestItemWrapper>
      <TextBox
        size={18}
        content={`[${questData.questType}] ${questData.questName} ${questData.questGoal}`}
      />
      <style.QuestInfoWrapper>
        <style.ColumContentWrapper>
          <style.ProgressWrapper>
            <style.CustomProgressBar
              bgColor={"#333333"}
              borderRadius={"0px"}
              completed={`${questData.progress}`}
            />
            <TextBox size={20} content={`${questData.progress}%`} />
          </style.ProgressWrapper>
          <QuestConfirmButton progress={parseInt(`${questData.progress}`)} />
        </style.ColumContentWrapper>
        <style.ImageWrapper>
          <style.CustomImage
            src={questData.award.imgUrl}
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
