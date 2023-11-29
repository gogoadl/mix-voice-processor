import instance from ".";

const getPost = (id: number) => {
  console.log("qwer");
  return instance({ url: `/api/v1/posts/${id}` });
};

export default { getPost };
