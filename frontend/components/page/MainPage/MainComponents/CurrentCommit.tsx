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
<<<<<<< HEAD
        content={`${nicknameData.nickname} made ${attendanceData.relayCnt} commits today.`}
=======
        content={`${nicknameData.nickname} visited ${
          attendanceData.relayCnt
        } time${attendanceData.relayCnt > 1 ? "s" : ""} today.`}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      />
    </style.CommitContainer>
  );
};
export default CurrentCommit;
