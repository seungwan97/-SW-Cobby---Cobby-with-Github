import * as style from "@/components/page/MainPage/MainComponents/style/ExpProgress";
interface Props {
  avatarData: {
    level: number;
    exp: number;
<<<<<<< HEAD
=======
    prevExp: number;
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    nextExp: number;
    outfits: {
      head: string;
      effect: string;
      body: string;
    };
  };
}
const ExpProgress = (props: Props) => {
  const { avatarData } = props;
<<<<<<< HEAD
  return (
    <>
      <style.FullDiv>
        <style.RangeDiv level={avatarData.exp} fullLevel={avatarData.nextExp} />
=======

  return (
    <>
      <style.FullDiv>
        <style.RangeDiv
          prevExp={avatarData.prevExp}
          exp={avatarData.exp}
          nextExp={avatarData.nextExp}
        />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      </style.FullDiv>
    </>
  );
};
export default ExpProgress;
