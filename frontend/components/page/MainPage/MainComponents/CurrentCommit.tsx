import * as style from "@/components/page/MainPage/MainComponents/style/CurrentCommit";
import TextBox from "@/components/common/TextBox/TextBox";

interface Props {
  nicknameData: {
    nickname: string;
  };
  attendanceData: {
    relayCnt: number;
  };
}

const CurrentCommit = (props: Props) => {
  const { nicknameData } = props;
  const { attendanceData } = props;
  return (
    <style.CommitContainer>
      <TextBox
        size={23}
        content={`${nicknameData.nickname} made ${attendanceData.relayCnt} commits today.`}
      />
    </style.CommitContainer>
  );
};
export default CurrentCommit;
