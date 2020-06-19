<?php

class M_user extends CI_Model
{
    function __construct()
    {
        parent::__construct();
    }

    public function register($id_u, $identity, $email, $password, $role, $date_created)
    {
        if ($email == "" || $password == "" || $identity == "") {
            return false;
        } else {
            $arraySave = array(
                'id_user' => $id_u,
                'identity' => $identity,
                'email' => $email,
                'password' => password_hash($password, PASSWORD_DEFAULT),
                'role_id' => $role,
                'date_created' => $date_created
            );

            $result = $this->db->insert("user", $arraySave);

            if ($result) {
                return true;
            } else {
                return false;
            }
        }
    }

    public function login($email, $password)
    {
        $this->db->where("email", $email);
        $this->db->limit(1);
        $query = $this->db->get("user");

        if ($query->num_rows() > 0) {
            $row = $query->row();

            if (password_verify($password, $row->password)) {
                $data[] = array(
                    'email' => $row->email,
                    'password' => $row->password
                );
                return $data;
            } else {
                return false;
            }
        }
    }
}
