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
<<<<<<< HEAD
}
const QuestItem = (props: Props) => {
  const { questData } = props;
  return (
    <style.QuestItemWrapper>
      <TextBox
        size={18}
=======
  modifyData: (qId: number) => void;
}
const QuestItem = (props: Props) => {
  const { questData } = props;
  const { modifyData } = props;
  return (
    <style.QuestItemWrapper>
      <TextBox
        size={25}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
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
<<<<<<< HEAD
            <TextBox size={20} content={`${questData.progress}%`} />
          </style.ProgressWrapper>
          <QuestConfirmButton progress={parseInt(`${questData.progress}`)} />
=======
            <TextBox
              size={20}
              content={`${questData.progress}%`}
            />
          </style.ProgressWrapper>
          <QuestConfirmButton
            modifyData={modifyData}
            questId={parseInt(`${questData.questId}`)}
            progress={parseInt(`${questData.progress}`)}
          />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
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
